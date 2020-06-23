package com.orionmiz.guildweb.persistence;

import com.orionmiz.guildweb.domain.WebBoard;
import org.springframework.data.repository.CrudRepository;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long> {
}
