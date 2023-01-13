package org.karbit.post.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.karbit.post.model.Post;
import org.karbit.post.model.PostableStatus;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao<P extends Post> extends MongoRepository<P, String> {
	Optional<P> findByUniqueId(String uniqueId);

	List<P> findByStatusInOrderByCreationDateDesc(Set<? extends PostableStatus> status);

	List<P> findByStatusInOrderByCreationDateDesc(Set<? extends PostableStatus> status, Pageable pageable);

	List<P> findByAuthorUserIdAndStatusInOrderByCreationDateDesc(String userId, Set<? extends PostableStatus> status, Pageable pageable);

	List<P> findByTitleLikeAndStatusInOrderByCreationDateDesc(String title, Set<? extends PostableStatus> status, Pageable pageable);
}
