package org.project.repository.interfaces;

import org.project.entity.Expert;

import java.util.List;

public interface ManageRepositoryForExpert {
    void changeStatusExpert(Expert expert);
    List<Expert> search(Expert expert);
    List<Expert> requestList();
}
