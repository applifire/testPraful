package com.app.shared.appbasicsetup.usermanagement;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
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

@Table(name = "ast_UserAccessLevel_M")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "root", versionNumber = "3", comments = "UserAccessLevel", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "UserAccessLevel.findAll", query = " select u from UserAccessLevel u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "UserAccessLevel.findById", query = "select e from UserAccessLevel e where e.systemInfo.activeStatus=1 and e.userAccessLevelId =:userAccessLevelId") })
public class UserAccessLevel implements Serializable, CommonEntityInterface, Comparator<UserAccessLevel> {

    private static final long serialVersionUID = 1471588722324L;

    @Column(name = "userAccessLevel")
    @JsonProperty("userAccessLevel")
    @NotNull
    @Min(0)
    @Max(99999)
    private Integer userAccessLevel;

    @Column(name = "levelName")
    @JsonProperty("levelName")
    @NotNull
    @Size(min = 3, max = 256)
    private String levelName;

    @Column(name = "levelDescription")
    @JsonProperty("levelDescription")
    @NotNull
    @Size(min = 3, max = 256)
    private String levelDescription;

    @Column(name = "levelHelp")
    @JsonProperty("levelHelp")
    @Size(min = 3, max = 2048)
    private String levelHelp;

    @Column(name = "levelIcon")
    @JsonProperty("levelIcon")
    @Size(min = 3, max = 256)
    private String levelIcon;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "userAccessLevelId")
    @JsonProperty("userAccessLevelId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 3, max = 64)
    private String userAccessLevelId;

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

    public Integer getUserAccessLevel() {
        return userAccessLevel;
    }

    public void setUserAccessLevel(Integer _userAccessLevel) {
        if (_userAccessLevel != null) {
            this.userAccessLevel = _userAccessLevel;
        }
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String _levelName) {
        if (_levelName != null) {
            this.levelName = _levelName;
        }
    }

    public String getLevelDescription() {
        return levelDescription;
    }

    public void setLevelDescription(String _levelDescription) {
        if (_levelDescription != null) {
            this.levelDescription = _levelDescription;
        }
    }

    public String getLevelHelp() {
        return levelHelp;
    }

    public void setLevelHelp(String _levelHelp) {
        this.levelHelp = _levelHelp;
    }

    public String getLevelIcon() {
        return levelIcon;
    }

    public void setLevelIcon(String _levelIcon) {
        this.levelIcon = _levelIcon;
    }

    public String getPrimaryKey() {
        return userAccessLevelId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return userAccessLevelId;
    }

    public String getUserAccessLevelId() {
        return userAccessLevelId;
    }

    public void setUserAccessLevelId(String _userAccessLevelId) {
        this.userAccessLevelId = _userAccessLevelId;
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
     * @param UserAccessLevel
     * @param UserAccessLevel
     */
    @Override
    public int compare(UserAccessLevel object1, UserAccessLevel object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((userAccessLevel == null ? " " : userAccessLevel) + ",").append((levelName == null ? " " : levelName) + ",").append((levelDescription == null ? " " : levelDescription));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (userAccessLevelId == null) {
            return super.hashCode();
        } else {
            return userAccessLevelId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.appbasicsetup.usermanagement.UserAccessLevel other = (com.app.shared.appbasicsetup.usermanagement.UserAccessLevel) obj;
            if (userAccessLevelId == null) {
                return false;
            } else if (!userAccessLevelId.equals(other.userAccessLevelId)) {
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
