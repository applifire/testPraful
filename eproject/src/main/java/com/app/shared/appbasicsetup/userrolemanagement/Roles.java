package com.app.shared.appbasicsetup.userrolemanagement;
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
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import com.app.shared.EntityAudit;
import javax.persistence.Embedded;
import com.app.shared.SystemInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Roles_T")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "root", updatedBy = "root", versionNumber = "3", comments = "Roles", complexity = Complexity.LOW)
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "roleId")
@NamedQueries({ @javax.persistence.NamedQuery(name = "Roles.findAll", query = " select u from Roles u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Roles.findById", query = "select e from Roles e where e.systemInfo.activeStatus=1 and e.roleId =:roleId") })
public class Roles implements Serializable, CommonEntityInterface, Comparator<Roles> {

    private static final long serialVersionUID = 1471588731825L;

    @Column(name = "RoleName")
    @JsonProperty("roleName")
    @NotNull
    @Size(min = 0, max = 256)
    private String roleName;

    @Column(name = "RoleDescription")
    @JsonProperty("roleDescription")
    @NotNull
    @Size(min = 0, max = 256)
    private String roleDescription;

    @Column(name = "RoleIcon")
    @JsonProperty("roleIcon")
    @Size(min = 0, max = 64)
    private String roleIcon;

    @Column(name = "roleHelp")
    @JsonProperty("roleHelp")
    @Size(min = 0, max = 256)
    private String roleHelp;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "roleId")
    @JsonProperty("roleId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 3, max = 64)
    private String roleId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "roles")
    private List<RoleMenuBridge> roleMenuBridge;

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String _roleName) {
        if (_roleName != null) {
            this.roleName = _roleName;
        }
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String _roleDescription) {
        if (_roleDescription != null) {
            this.roleDescription = _roleDescription;
        }
    }

    public String getRoleIcon() {
        return roleIcon;
    }

    public void setRoleIcon(String _roleIcon) {
        this.roleIcon = _roleIcon;
    }

    public String getRoleHelp() {
        return roleHelp;
    }

    public void setRoleHelp(String _roleHelp) {
        this.roleHelp = _roleHelp;
    }

    public String getPrimaryKey() {
        return roleId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return roleId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String _roleId) {
        this.roleId = _roleId;
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

    public Roles addRoleMenuBridge(RoleMenuBridge _roleMenuBridge) {
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge>();
        }
        if (_roleMenuBridge != null) {
            _roleMenuBridge.setRoles(this);
            this.roleMenuBridge.add(_roleMenuBridge);
        }
        return this;
    }

    public Roles removeRoleMenuBridge(RoleMenuBridge _roleMenuBridge) {
        if (this.roleMenuBridge != null) {
            this.roleMenuBridge.remove(_roleMenuBridge);
        }
        return this;
    }

    public Roles addAllRoleMenuBridge(List<RoleMenuBridge> _roleMenuBridge) {
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge>();
        }
        if (_roleMenuBridge != null) {
            for (int i = 0; i < _roleMenuBridge.size(); i++) {
                _roleMenuBridge.get(i).setRoles(this);
            }
            this.roleMenuBridge.addAll(_roleMenuBridge);
        }
        return this;
    }

    @JsonIgnore
    public Integer getTotalNumberOfRoleMenuBridge() {
        if (this.roleMenuBridge != null) {
            return this.roleMenuBridge.size();
        }
        return 0;
    }

    public List<RoleMenuBridge> getRoleMenuBridge() {
        return roleMenuBridge;
    }

    public void setRoleMenuBridge(List<RoleMenuBridge> _roleMenuBridge) {
        for (int i = 0; i < _roleMenuBridge.size(); i++) {
            if (_roleMenuBridge.get(i).getRoles() == null) {
                _roleMenuBridge.get(i).setRoles(this);
            }
        }
        this.roleMenuBridge = _roleMenuBridge;
    }

    @JsonIgnore
    public List<RoleMenuBridge> getDeletedRoleMenuBridgeList() {
        List<RoleMenuBridge> rolemenubridgeToRemove = new java.util.ArrayList<RoleMenuBridge>();
        for (RoleMenuBridge _rolemenubridge : roleMenuBridge) {
            if (_rolemenubridge.isHardDelete()) {
                rolemenubridgeToRemove.add(_rolemenubridge);
            }
        }
        roleMenuBridge.removeAll(rolemenubridgeToRemove);
        return rolemenubridgeToRemove;
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
        setValidatorroleMenuBridge(_validateFactory);
    }

    private void setValidatorroleMenuBridge(EntityValidatorHelper<Object> _validateFactory) {
        if (roleMenuBridge != null) {
            for (int i = 0; i < roleMenuBridge.size(); i++) {
                roleMenuBridge.get(i).setEntityValidator(_validateFactory);
            }
        }
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
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        }
        for (RoleMenuBridge _roleMenuBridge : roleMenuBridge) {
            _roleMenuBridge.setEntityAudit(customerId, userId, recordType);
            _roleMenuBridge.setSystemInformation(recordType);
        }
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
        if (this.roleMenuBridge == null) {
            this.roleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        }
        for (RoleMenuBridge _roleMenuBridge : roleMenuBridge) {
            _roleMenuBridge.setEntityAudit(customerId, userId);
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
            this.systemInfo.setActiveStatus(-1);
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
     * @param Roles
     * @param Roles
     */
    @Override
    public int compare(Roles object1, Roles object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((roleName == null ? " " : roleName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (roleId == null) {
            return super.hashCode();
        } else {
            return roleId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            com.app.shared.appbasicsetup.userrolemanagement.Roles other = (com.app.shared.appbasicsetup.userrolemanagement.Roles) obj;
            if (roleId == null) {
                return false;
            } else if (!roleId.equals(other.roleId)) {
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
