/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   SimpleExperimentPackageConnectionDao.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentPackage;
import cz.zcu.kiv.eegdatabase.data.pojo.ExperimentPackageConnection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate implementation of ExperimentPackageConnectionDao
 *
 * @author bydga
 */
public class SimpleExperimentPackageConnectionDao extends SimpleGenericDao<ExperimentPackageConnection, Integer> implements ExperimentPackageConnectionDao {

	public SimpleExperimentPackageConnectionDao() {
		super(ExperimentPackageConnection.class);
	}

	@Override
	public List<Experiment> listExperimentsByPackage(int packageId) {
		String HQL = "select e from Experiment e, ExperimentPackageConnection epc left join fetch e.scenario "
				+ "where e.experimentId = epc.experiment.experimentId "
				+ "AND epc.experimentPackage.experimentPackageId = :packageId";

		Query query = getSessionFactory().getCurrentSession().createQuery(HQL);
		query.setParameter("packageId", packageId);
		return query.list();
	}

	@Override
	public List<Experiment> listExperimentsWithoutPackage(int researchGroupId, int packageId) {
		String HQL = "SELECT e FROM Experiment e "
				+ "LEFT JOIN fetch e.scenario "
				+ "WHERE e.experimentId NOT IN "
				+ "(SELECT epc.experiment.experimentId FROM ExperimentPackageConnection epc "
				+ "   WHERE epc.experimentPackage.experimentPackageId = :packageId) "
				+ "AND e.researchGroup.researchGroupId = :researchGroupId";
		Query query = getSessionFactory().getCurrentSession().createQuery(HQL);
		query.setParameter("packageId", packageId);
		query.setParameter("researchGroupId", researchGroupId);
		return query.list();
	}

	@Override
	public boolean isExperimentInPackage(int experimentId, int packageId) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(type);
		criteria.add(Restrictions.eq("experiment.experimentId", experimentId));
		criteria.add(Restrictions.eq("experimentPackage.experimentPackageId", packageId));
		criteria.setProjection(Projections.rowCount());
		int count = ((Number) criteria.uniqueResult()).intValue();
		return count > 0;
	}

	@Override
	public boolean removeExperimentFromPackage(int experimentId, int packageId) {
		String hqlQuery = "delete from ExperimentPackageConnection epc where epc.experiment = :experiment and epc.experimentPackage = :package";
		int i = this.getSession().createQuery(hqlQuery).setInteger("experiment", experimentId).setInteger("package", packageId).executeUpdate();
		return i > 0;
	}

	@Override
	public Integer create(ExperimentPackageConnection newInstance) {
		boolean present = this.isExperimentInPackage(newInstance.getExperiment().getExperimentId(),
				newInstance.getExperimentPackage().getExperimentPackageId());
		if (present) {
			//already there
			return -1;
		}

		return super.create(newInstance);
	}

	@Override
	public void removeAllConnections(ExperimentPackage pack) {
		String hqlQuery = "delete from ExperimentPackageConnection epc where epc.experimentPackage = :ep";
		this.getSession().createQuery(hqlQuery).setInteger("ep", pack.getExperimentPackageId()).executeUpdate();
	}

	@Override
	public List<Experiment> listExperimentsWithoutPackage() {
		String HQL = "SELECT e FROM Experiment e "
				+ "LEFT JOIN fetch e.scenario "
				+ "WHERE e.experimentId NOT IN "
				+ "(SELECT epc.experiment.experimentId FROM ExperimentPackageConnection epc)";
		Query query = getSessionFactory().getCurrentSession().createQuery(HQL);
		return query.list();
	}
	
	@Override
    public List<Experiment> listExperimentsWithoutPackageWithLicense(int licenseId) {
        String HQL = "SELECT e FROM Experiment e "
                + "LEFT JOIN fetch e.scenario "
                + "WHERE e.experimentId NOT IN "
                + "(SELECT epc.experiment.experimentId FROM ExperimentPackageConnection epc) "
                + "AND e.experimentId IN (SELECT el.experiment.experimentId FROM ExperimentLicence el WHERE el.license.licenseId = :licenseId)";
        Query query = getSessionFactory().getCurrentSession().createQuery(HQL);
        query.setParameter("licenseId", licenseId);
        return query.list();
    }
}
