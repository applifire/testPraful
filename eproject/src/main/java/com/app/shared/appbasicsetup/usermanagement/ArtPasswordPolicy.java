package com.app.shared.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "art_password_policy")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "", versionNumber = "1", comments = "PasswordPolicy", complexity = Complexity.LOW)
@NamedQueries({
		@javax.persistence.NamedQuery(name = "PasswordPolicy.DefaultFinders", query = "select e from PasswordPolicy e where e.systemInfo.activeStatus=1 and e.policyName LIKE :policyName"),
		@javax.persistence.NamedQuery(name = "PasswordPolicy.findById", query = "select e from PasswordPolicy e where e.systemInfo.activeStatus=1 and e.policyId =:policyId") })
public class ArtPasswordPolicy implements Serializable, Comparator<ArtPasswordPolicy> {

	private static final long serialVersionUID = 1L;

	@Column(name = "policyName")
	@JsonProperty("policyName")
	@NotNull
	@Size(min = 0, max = 256)
	private String policyName;

	@Column(name = "policyDescription")
	@JsonProperty("policyDescription")
	@Size(min = 0, max = 256)
	private String policyDescription;

	@Column(name = "maxPwdLength")
	@JsonProperty("maxPwdLength")
	@Min(0)
	@Max(32)
	private Integer maxPwdLength;

	@Column(name = "minPwdLength")
	@JsonProperty("minPwdLength")
	@NotNull
	@Min(0)
	@Max(11)
	private Integer minPwdLength;

	@Column(name = "minCapitalLetters")
	@JsonProperty("minCapitalLetters")
	@Min(0)
	@Max(11)
	private Integer minCapitalLetters;

	@Column(name = "minSmallLetters")
	@JsonProperty("minSmallLetters")
	@Min(0)
	@Max(11)
	private Integer minSmallLetters;

	@Column(name = "minNumericValues")
	@JsonProperty("minNumericValues")
	@Min(0)
	@Max(11)
	private Integer minNumericValues;

	@Column(name = "minSpecialLetters")
	@JsonProperty("minSpecialLetters")
	@Min(0)
	@Max(11)
	private Integer minSpecialLetters;

	@Column(name = "allowedSpecialLetters")
	@JsonProperty("allowedSpecialLetters")
	@Size(min = 0, max = 256)
	private String allowedSpecialLetters;

	@Transient
	private String primaryKey;

	@Id
	@Column(name = "policyId")
	@JsonProperty("policyId")
	@GeneratedValue(generator = "UUIDGenerator")
	@Size(min = 0, max = 64)
	private String policyId;

	@Transient
	@JsonIgnore
	private EntityValidatorHelper<Object> entityValidator;

	@Version
	@Column(name = "version_id")
	@JsonProperty("versionId")
	private int versionId;

	@Transient
	private String primaryDisplay;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(final String _policyName) {
		if (_policyName != null) {
			this.policyName = _policyName;
		}
	}

	public String getPolicyDescription() {
		return policyDescription;
	}

	public void setPolicyDescription(final String _policyDescription) {
		this.policyDescription = _policyDescription;
	}

	public Integer getMaxPwdLength() {
		return maxPwdLength;
	}

	public void setMaxPwdLength(final Integer _maxPwdLength) {
		this.maxPwdLength = _maxPwdLength;
	}

	public Integer getMinPwdLength() {
		return minPwdLength;
	}

	public void setMinPwdLength(final Integer _minPwdLength) {
		if (_minPwdLength != null) {
			this.minPwdLength = _minPwdLength;
		}
	}

	public Integer getMinCapitalLetters() {
		return minCapitalLetters;
	}

	public void setMinCapitalLetters(final Integer _minCapitalLetters) {
		this.minCapitalLetters = _minCapitalLetters;
	}

	public Integer getMinSmallLetters() {
		return minSmallLetters;
	}

	public void setMinSmallLetters(final Integer _minSmallLetters) {
		this.minSmallLetters = _minSmallLetters;
	}

	public Integer getMinNumericValues() {
		return minNumericValues;
	}

	public void setMinNumericValues(final Integer _minNumericValues) {
		this.minNumericValues = _minNumericValues;
	}

	public Integer getMinSpecialLetters() {
		return minSpecialLetters;
	}

	public void setMinSpecialLetters(final Integer _minSpecialLetters) {
		this.minSpecialLetters = _minSpecialLetters;
	}

	public String getAllowedSpecialLetters() {
		return allowedSpecialLetters;
	}

	public void setAllowedSpecialLetters(final String _allowedSpecialLetters) {
		this.allowedSpecialLetters = _allowedSpecialLetters;
	}

	public String getPrimaryKey() {
		return policyId;
	}

	public void setPrimaryKey(final String _primaryKey) {
		this.primaryKey = _primaryKey;
	}

	public String _getPrimarykey() {
		return policyId;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(final String _policyId) {
		this.policyId = _policyId;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(final int _versionId) {
		this.versionId = _versionId;
	}

	public void setPrimaryDisplay(final String _primaryDisplay) {
		this.primaryDisplay = _primaryDisplay;
	}

	@Override
	public int compare(ArtPasswordPolicy object1, ArtPasswordPolicy object2) {
		return 0;
	}

	public String getPrimaryDisplay() {
		StringBuilder data = new StringBuilder().append("").append((policyName == null ? " " : policyName));
		return data.toString();
	}

	public String toString() {
		return getPrimaryDisplay();
	}

	public int hashCode() {
		if (policyId == null) {
			return super.hashCode();
		} else {
			return policyId.hashCode();
		}
	}

	public boolean equals(final Object obj) {
		try {
			final ArtPasswordPolicy other = (ArtPasswordPolicy) obj;
			if (policyId == null) {
				return false;
			} else if (!policyId.equals(other.policyId)) {
				return false;
			}
		} catch (java.lang.Exception ignore) {
			return false;
		}
		return true;
	}
}
