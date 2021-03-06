package com.app.server.repository.organization.locationmanagement;
import com.app.server.repository.core.SearchInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;
import java.util.List;

@SourceCodeAuthorClass(createdBy = "root", updatedBy = "root", versionNumber = "3", comments = "Repository for Address Transaction table", complexity = Complexity.MEDIUM)
public interface AddressRepository<T> extends SearchInterface {

    List<T> findAll() throws Exception;

    T save(T entity) throws Exception;

    List<T> save(List<T> entity) throws Exception;

    void delete(String id) throws Exception;

    void update(T entity) throws Exception;

    void update(List<T> entity) throws Exception;

    List<T> findByAddressTypeId(String addressTypeId) throws Exception;

    List<T> findByCountryId(String countryId) throws Exception;

    List<T> findByStateId(String stateId) throws Exception;

    List<T> findByCityId(String cityId) throws Exception;

    T findById(String addressId) throws Exception;
}
