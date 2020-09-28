import React, { useEffect, useState } from "react";
import "axios";

import PostList from "../component/post-list";
import PostPublishingForm from "../component/post-publish-form";
import { GuestbookPost, listAllPosts, publishPost } from "../api/post";

export default function HomePage() {
  const [posts, setPosts] = useState<GuestbookPost[]>([]);

  const handleSubmit = (content: string) => {
    publishPost(content).then(() => refreshPosts(setPosts));
  };

  useEffect(() => {
    refreshPosts(setPosts);
  }, []);

  return (
    <div>
      <h1>방명록</h1>
      <h2>방명록 작성</h2>
      <PostPublishingForm onSubmit={handleSubmit} />
      <h2>작성된 글</h2>
      <PostList posts={posts} />
    </div>
  );
}

function refreshPosts(
  setPosts: React.Dispatch<React.SetStateAction<GuestbookPost[]>>
) {
  listAllPosts().then((posts) => setPosts(posts));
}
