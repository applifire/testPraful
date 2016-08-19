package com.app.shared.organization.locationmanagement;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Country_M")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "root", versionNumber = "3", comments = "Country", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Country.findAll", query = " select u from Country u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Country.DefaultFinders", query = "select e from Country e where e.systemInfo.activeStatus=1 and e.countryName LIKE :countryName and e.countryCode1 LIKE :countryCode1"), @javax.persistence.NamedQuery(name = "Country.findById", query = "select e from Country e where e.systemInfo.activeStatus=1 and e.countryId =:countryId") })
public class Country implements Serializable, CommonEntityInterface, Comparator<Country> {

    private static final long serialVersionUID = 1471588704572L;

    @Column(name = "countryName")
    @JsonProperty("countryName")
    @NotNull
    @Size(min = 0, max = 128)
    private String countryName;

    @Column(name = "countryCode1")
    @JsonProperty("countryCode1")
    @Size(min = 0, max = 3)
    private String countryCode1;

    @Column(name = "countryCode2")
    @JsonProperty("countryCode2")
    @Size(min = 0, max = 3)
    private String countryCode2;

    @Column(name = "countryFlag")
    @JsonProperty("countryFlag")
    @Size(min = 0, max = 64)
    private String countryFlag;

    @Column(name = "capital")
    @JsonProperty("capital")
    @Size(min = 0, max = 32)
    private String capital;

    @Column(name = "currencyCode")
    @JsonProperty("currencyCode")
    @Size(min = 0, max = 3)
    private String currencyCode;

    @Column(name = "currencyName")
    @JsonProperty("currencyName")
    @Size(min = 0, max = 128)
    private String currencyName;

    @Column(name = "currencySymbol")
    @JsonProperty("currencySymbol")
    @Size(min = 0, max = 32)
    private String currencySymbol;

    @Column(name = "capitalLatitude")
    @JsonProperty("capitalLatitude")
    @Min(0)
    @Max(11)
    private Integer capitalLatitude;

    @Column(name = "capitalLongitude")
    @JsonProperty("capitalLongitude")
    @Min(0)
    @Max(11)
    private Integer capitalLongitude;

    @Column(name = "isoNumeric")
    @JsonProperty("isoNumeric")
    @Min(0)
    @Max(1000)
    private Integer isoNumeric;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "countryId")
    @JsonProperty("countryId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 0, max = 64)
    private String countryId;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String _countryName) {
        if (_countryName != null) {
            this.countryName = _countryName;
        }
    }

    public String getCountryCode1() {
        return countryCode1;
    }

    public void setCountryCode1(String _countryCode1) {
        this.countryCode1 = _countryCode1;
    }

    public String getCountryCode2() {
        return countryCode2;
    }

    public void setCountryCode2(String _countryCode2) {
        this.countryCode2 = _countryCode2;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String _countryFlag) {
        this.countryFlag = _countryFlag;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String _capital) {
        this.capital = _capital;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String _currencyCode) {
        this.currencyCode = _currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String _currencyName) {
        this.currencyName = _currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String _currencySymbol) {
        this.currencySymbol = _currencySymbol;
    }

    public Integer getCapitalLatitude() {
        return capitalLatitude;
    }

    public void setCapitalLatitude(Integer _capitalLatitude) {
        this.capitalLatitude = _capitalLatitude;
    }

    public Integer getCapitalLongitude() {
        return capitalLongitude;
    }

    public void setCapitalLongitude(Integer _capitalLongitude) {
        this.capitalLongitude = _capitalLongitude;
    }

    public Integer getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(Integer _isoNumeric) {
        this.isoNumeric = _isoNumeric;
    }

    public String getPrimaryKey() {
        return countryId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return countryId;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String _countryId) {
        this.countryId = _countryId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    /**
     * Returns boolean value if System information's active status =-1
     * @return boolean
     */
    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates the entity fields based on java.validation.constraints annotaions and sets isEntityValided value in System information object
     * @return boolean
     * @throws java.lang.SecurityException
     */
    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    /**
     * Sets EntityValidator object
     * @param _validateFactory
     */
    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    /**
     * Creates a new entity audit object and  if primarykey fields are null then sets created by, updated by, active status values in the entity audit field.
     * @param customerId
     * @param userId
     */
    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    /**
     * Creates a new entity audit object and System Information object and based on @Params RECORD_TYPE sets created by and updated by values in the entity audit field.
     * @param CustomerId
     * @param userId
     * @param RECORD_TYPE
     */
    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    /**
     * Returns Logged in user informatio.
     * @return auditInfo
     */
    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    /**
     * Creates new System Information object.
     * @param RECORD_TYPE
     */
    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    /**
     * Sets active status in System Information object.
     * @param activeStatus
     */
    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    /**
     * Returns system information object.
     * @return systemInfo
     */
    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    /**
     * Creates System information obect if null and sets transaction access code in that object.
     * @param transactionAccessCode
     */
    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    /**
     * Compares 2 objects and returns integer value
     * @param Country
     * @param Country
     */
    @Override
    public int compare(Country object1, Country object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((countryName == null ? " " : countryName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (countryId == null) {
            return super.hashCode();
        } else {
            return countryId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.organization.locationmanagement.Country other = (com.app.shared.organization.locationmanagement.Country) obj;
            if (countryId == null) {
                return false;
            } else if (!countryId.equals(other.countryId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
