package org.karbit.post.biz;

import org.karbit.post.biz.dto.AuthorProfile;
import org.karbit.post.dao.PostDao;
import org.karbit.post.model.Post;
import org.slf4j.Logger;

interface PostManager<P extends Post> {
	Logger getLogger();

	PostDao<P> getDao();

	AuthorProfile getAuthorProfile(String authorUserId);

}
