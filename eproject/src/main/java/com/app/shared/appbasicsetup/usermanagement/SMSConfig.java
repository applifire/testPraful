package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

import com.athena.shared.pluggable.entity.AuditPropertiesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "art_SMSConfig_M")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "SMSConfig", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "SMSConfig.findById", query = "select e from SMSConfig e where e.activeStatus=1 and e.configId =:configId") })
public class SMSConfig extends AuditPropertiesEntity implements Serializable {

	private static final long serialVersionUID = 1467809935992L;

	@Column(name = "configName")
	@JsonProperty("configName")
	@NotNull
	@Size(min = 1, max = 256)
	private String configName;

	@Column(name = "jsonData")
	@JsonProperty("jsonData")
	@NotNull
	@Size(min = 1, max = 256)
	private String jsonData;

	@Column(name = "configurationBean")
	@JsonProperty("configurationBean")
	@NotNull
	@Size(min = 1, max = 256)
	private String configurationBean;

	@Column(name = "baseUrl")
	@JsonProperty("baseUrl")
	@NotNull
	@Size(min = 1, max = 256)
	private String baseUrl;

	@Id
	@Column(name = "configId")
	@JsonProperty("configId")
	@GeneratedValue(generator = "UUIDGenerator")
	private String configId;

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String _configName) {
		if (_configName != null) {
			this.configName = _configName;
		}
	}

	public String getJsonData() throws Exception {
		return this.decodeJSON(jsonData);
	}

	public void setJsonData(String _jsonData) throws Exception {
		if (_jsonData != null) {
			this.jsonData = this.encodeJSON(_jsonData);
		}
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String _baseUrl) {
		if (_baseUrl != null) {
			this.baseUrl = _baseUrl;
		}
	}

	public String _getPrimarykey() {
		return configId;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String _configId) {
		this.configId = _configId;
	}

	public String getConfigurationBean() {
		return configurationBean;
	}

	public void setConfigurationBean(String _configurationBean) {
		this.configurationBean = _configurationBean;
	}

}
