package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.config.annotation.Complexity;

import com.app.config.annotation.SourceCodeAuthorClass;

import com.app.server.repository.core.SearchInterface;

import java.util.List;

@SourceCodeAuthorClass(createdBy = "", updatedBy = "", versionNumber = "1", comments = "Repository for SMSNotification Master table Entity", complexity = Complexity.LOW)
public interface SMSNotificationRepository<T> extends SearchInterface {

	List<T> findAll() throws Exception;

	T save(T entity) throws Exception;

	List<T> save(List<T> entity) throws Exception;

	void delete(String id) throws Exception;

	void update(T entity) throws Exception;

	void update(List<T> entity) throws Exception;

	T findById(String notificationId) throws Exception;
}
