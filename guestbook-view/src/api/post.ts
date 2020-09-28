import Axios from "axios";

export interface GuestbookPost {
  id: number;
  content: string;
}

export function listAllPosts(): Promise<GuestbookPost[]> {
  return Axios.get("/api/v1/guestbook/posts").then(
    (res) => res.data.posts
  );
}

export function publishPost(content: string): Promise<string> {
  return Axios.post("/api/v1/guestbook/posts", {
    content,
  }).then((res) => res.headers.location);
}
