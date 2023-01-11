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
	P findByUniqueId(String uniqueId);

	List<P> findByStatusIn(Set<PostableStatus> status);

	List<P> findByStatusIn(Set<PostableStatus> status, Pageable pageable);

	List<P> findByAuthorIdAndStatusIn(String userId, Set<PostableStatus> status, Pageable pageable);

	List<P> findByTitleLikeAndStatusIn(String title, Set<PostableStatus> status, Pageable pageable);
}
