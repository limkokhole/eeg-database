package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Hardware;
import cz.zcu.kiv.eegdatabase.data.pojo.HardwareGroupRel;

import java.util.List;

public class SimpleHardwareDao extends SimpleGenericDao<Hardware, Integer> implements HardwareDao {
    public SimpleHardwareDao() {
        super(Hardware.class);
    }

    public void createDefaultRecord(Hardware hardware){
        hardware.setDefaultNumber(1);
        create(hardware);
    }

    public void createGroupRecord(HardwareGroupRel hardwareGroupRel){
        hardwareGroupRel.getHardware().setDefaultNumber(0);
        getHibernateTemplate().save(hardwareGroupRel);
    }

    public List<Hardware> getItemsForList() {
        String hqlQuery = "from Hardware h order by h.title, h.type";
        List<Hardware> list = getHibernateTemplate().find(hqlQuery);
        return list;
    }

    public List<Hardware> getRecordsNewerThan(long oracleScn) {
        String hqlQuery = "from Hardware h where h.scn > :oracleScn";
        List<Hardware> list = getHibernateTemplate().findByNamedParam(hqlQuery, "oracleScn", oracleScn);
        return list;
    }

    public List<Hardware> getRecordsByGroup(int groupId){
        String hqlQuery = "from Hardware h inner join fetch h.researchGroups as rg where rg.researchGroupId='"+groupId+"'";
        List<Hardware> list = getHibernateTemplate().find(hqlQuery);
        return list;
    }

    public List<Hardware> getDefaultRecords(){
        String hqlQuery = "from Hardware h where h.defaultNumber=1";
        List<Hardware> list = getHibernateTemplate().find(hqlQuery);
        return list;
    }

    public boolean canSaveTitle(String title, int id) {
        String hqlQuery = "from Hardware h where h.title = :title and h.hardwareId != :id";
        String[] names = {"title", "id"};
        Object[] values = {title, id};
        List<Hardware> list = getHibernateTemplate().findByNamedParam(hqlQuery, names, values);
        return (list.size() == 0);
    }

    /**
     * Title of hardware must be unique
     *
     * @param title - hardware title
     * @return
     */
    public boolean canSaveTitle(String title) {
        String hqlQuery = "from Hardware h where h.title = :title";
        String name = "title";
        Object value = title;
        List<Hardware> list = getHibernateTemplate().findByNamedParam(hqlQuery, name, value);
        return (list.size() == 0);
    }

    public boolean canDelete(int id) {
        String hqlQuery = "select h.experiments from Hardware h where h.hardwareId = :id";
        String[] names = {"id"};
        Object[] values = {id};
        List<Hardware> list = getHibernateTemplate().findByNamedParam(hqlQuery, names, values);
        return (list.size() == 0);
    }
}