import React from "react";

import { GuestbookPost } from "../api/post";

import "./post-list.css";

export default function PostList(props: {posts: GuestbookPost[]}) {
  return (
    <ul>
      {props.posts.map((post) => (
        <li key={post.id}>{post.content}</li>
      ))}
    </ul>
  );
}
