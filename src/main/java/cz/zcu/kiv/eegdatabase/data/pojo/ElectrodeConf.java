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
 *   ElectrodeConf.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.GenericGenerator;

import cz.zcu.kiv.formgen.annotation.FormId;
import cz.zcu.kiv.formgen.annotation.FormItem;
import cz.zcu.kiv.formgen.annotation.PreviewLevel;

/**
 * ElectrodeConf generated by hbm2java
 */
@Entity
@Table(name = "ELECTRODE_CONF")
public class ElectrodeConf implements Serializable {

	@FormId
	private int electrodeConfId;
	@FormItem(preview = PreviewLevel.MAJOR)
	private int impedance;
	@FormItem
	private ElectrodeSystem electrodeSystem;
	private DataFile descImg;
	private Set<ElectrodeLocation> electrodeLocations = new HashSet<ElectrodeLocation>(
			0);
	private Set<Experiment> experiments = new HashSet<Experiment>(0);

	public ElectrodeConf() {
	}

	public ElectrodeConf(int impedance, ElectrodeSystem electrodeSystem,
			DataFile descImg, Set<ElectrodeLocation> electrodeLocations,
			Set<Experiment> experiments) {
		this.impedance = impedance;
		this.electrodeSystem = electrodeSystem;
		this.descImg = descImg;
		this.electrodeLocations = electrodeLocations;
		this.experiments = experiments;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ELECTRODE_CONF_ID", nullable = false, precision = 22, scale = 0)
	public int getElectrodeConfId() {
		return this.electrodeConfId;
	}

	public void setElectrodeConfId(int electrodeConfId) {
		this.electrodeConfId = electrodeConfId;
	}

	@Column(name = "IMPEDANCE", precision = 22, scale = 0)
	public int getImpedance() {
		return this.impedance;
	}

	public void setImpedance(int impedance) {
		this.impedance = impedance;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ELECTRODE_SYSTEM_ID")
	public ElectrodeSystem getElectrodeSystem() {
		return this.electrodeSystem;
	}

	public void setElectrodeSystem(ElectrodeSystem electrodeSystem) {
		this.electrodeSystem = electrodeSystem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DESC_IMG_ID")
	public DataFile getDescImg() {
		return this.descImg;
	}

	public void setDescImg(DataFile descImg) {
		this.descImg = descImg;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ELECTRODE_LOCATION_REL", joinColumns = { @JoinColumn(name = "ELECTRODE_CONF_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ELECTRODE_LOCATION_ID", nullable = false, updatable = false) })
	public Set<ElectrodeLocation> getElectrodeLocations() {
		return this.electrodeLocations;
	}

	public void setElectrodeLocations(Set<ElectrodeLocation> electrodeLocations) {
		this.electrodeLocations = electrodeLocations;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "electrodeConf")
	public Set<Experiment> getExperiments() {
		return this.experiments;
	}

	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}

}
