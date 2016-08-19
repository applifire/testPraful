package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.athena.shared.pluggable.entity.AuditPropertiesEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Table(name = "art_SMSNotification_M")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "john.doe", updatedBy = "john.doe", versionNumber = "2", comments = "SMSNotification", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "SMSNotification.findById", query = "select e from SMSNotification e where e.activeStatus=1 and e.notificationId =:notificationId") })
public class SMSNotification extends AuditPropertiesEntity implements Serializable {

	private static final long serialVersionUID = 1467809936418L;

	@Column(name = "mobileNo")
	@JsonProperty("mobileNo")
	@Size(min = 1, max = 256)
	private String mobileNo;

	@Column(name = "smsText")
	@JsonProperty("smsText")
	@Size(min = 1, max = 256)
	private String smsText;

	@Column(name = "isSent")
	@JsonProperty("isSent")
	private Boolean isSent;

	@Column(name = "sendDate")
	@JsonProperty("sendDate")
	@JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
	@JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
	private Timestamp sendDate;

	@Column(name = "responseStatus")
	@JsonProperty("responseStatus")
	@Size(min = 1, max = 256)
	private String responseStatus;

	@Transient
	private String primaryKey;

	@Id
	@Column(name = "notificationId")
	@JsonProperty("notificationId")
	@GeneratedValue(generator = "UUIDGenerator")
	private String notificationId;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String _mobileNo) {
		this.mobileNo = _mobileNo;
	}

	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String _smsText) {
		this.smsText = _smsText;
	}

	public Boolean getIsSent() {
		return isSent;
	}

	public void setIsSent(Boolean _isSent) {
		this.isSent = _isSent;
	}

	public Timestamp getSendDate() {
		return sendDate;
	}

	public void setSendDate(Timestamp _sendDate) {
		this.sendDate = _sendDate;
	}

	public String getResponseStatus() throws Exception {
		return this.decodeJSON(responseStatus);
	}

	public void setResponseStatus(String _responseStatus) throws Exception {
		this.responseStatus = this.encodeJSON(_responseStatus);
	}

	public String getPrimaryKey() {
		return notificationId;
	}

	public void setPrimaryKey(String _primaryKey) {
		this.primaryKey = _primaryKey;
	}

	public String _getPrimarykey() {
		return notificationId;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String _notificationId) {
		this.notificationId = _notificationId;
	}

}
