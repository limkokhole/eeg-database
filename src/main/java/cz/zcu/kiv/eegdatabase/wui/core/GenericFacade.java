package cz.zcu.kiv.eegdatabase.wui.core;

import java.io.Serializable;
import java.util.List;

public interface GenericFacade<T, PK extends Serializable> {

    PK create(T newInstance);

    T read(PK id);

    List<T> readByParameter(String parameterName, int parameterValue);

    List<T> readByParameter(String parameterName, String parameterValue);

    void update(T transientObject);

    void delete(T persistentObject);

    List<T> getAllRecords();

    List<T> getRecordsAtSides(int first, int max);

    int getCountRecords();
}