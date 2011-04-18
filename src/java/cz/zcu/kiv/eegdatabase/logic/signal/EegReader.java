package cz.zcu.kiv.eegdatabase.logic.signal;

import cz.zcu.kiv.eegdatabase.data.pojo.DataFile;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.SQLException;

public class EegReader {

    private VhdrReader info;
    private int channelCnt;
    private int dataShift;
    private Class binaryType;
    private byte[] eegData;

    public EegReader(VhdrReader info) {
        this.info = info;
        try {
        dataShift = Integer.parseInt(info.getProperties().get("CI").get("Prestimulus"));
        } catch (NumberFormatException e) {
            dataShift = 1000;
        }
        channelCnt = Integer.parseInt(info.getProperties().get("CI").get("NumberOfChannels"));
        if (info.getProperties().get("BI").get("BinaryFormat").equals("INT_16")) {
            binaryType = Integer.class;
        }
        if (info.getProperties().get("BI").get("BinaryFormat").equals("IEEE_FLOAT_32")) {
            binaryType = Float.class;
        }
    }

    public double[] readFile(DataFile binaryFile) throws SQLException {
        eegData = binaryFile.getFileContent().getBytes(1, (int) binaryFile.getFileContent().length());
        int channel = 5; // electrode
        
        return readOneChannel(-dataShift, channel);
    }

    private double[] readOneChannel(int time, int channel) {

        int len = eegData.length / (getBinarySize() * channelCnt);
        double[] ret;
        double[] channelSet = new double[channelCnt];
        double resolution = info.getChannels().get(channel).getResolution();
        ret = new double[len];
        for (int i = 0; i < len; i++) {
            channelSet = readChannelSet(time + dataShift + i, channelSet);
            ret[i] = (channelSet[channel - 1] * resolution);
        }
        
        return ret;
    }

    private double[] readChannelSet(int dataPos, double[] channelSet) {
        int index = dataPos * getBinarySize() * channelCnt;
        for (int i = 0; i < channelCnt; i++) {
            if (getBinarySize() == 2) {
                ByteBuffer bb = ByteBuffer.allocate(2);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                bb.put(eegData[index]);
                bb.put(eegData[index + 1]);
                channelSet[i] = bb.getShort(0);
                index = index + 2;
            }
            else {
                 ByteBuffer bb = ByteBuffer.allocate(4);
                bb.order(ByteOrder.LITTLE_ENDIAN);
                bb.put(eegData[index]);
                bb.put(eegData[index + 1]);
                bb.put(eegData[index + 2]);
                bb.put(eegData[index + 3]);
                channelSet[i] = bb.getFloat(0);
                index = index + 4;
            }


        }
        return channelSet;
    }

    private int getBinarySize() {
        if (binaryType.equals(Float.class)) {
            return 4;
        }

        return 2; //default int16
    }
    
}
