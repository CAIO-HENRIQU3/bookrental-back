package com.caiohenrique.bookrental.validations.publishers;

import com.caiohenrique.bookrental.entities.PublishersEntity;
import com.caiohenrique.bookrental.io.publishers.PublishersUpdateRequest;

public interface PublishersEntityValidator {
    void validateForCreatePublishers(PublishersEntity entity);

    void validateForDeletePublishers(Integer id);

    void validateForUpdatePublishers(PublishersEntity entity, PublishersUpdateRequest updateRequest);
}
