import React, { useState } from "react";

export default function PostPublishingForm(props: {
  onSubmit: (content: string) => void;
}) {
  const [inputElement, setInputElement] = useState<HTMLInputElement | null>(
    null
  );
  const [contentInput, setContentInput] = useState("");

  const handleContentsInputChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setContentInput(event.target.value);
  };

  const handleSubmitButtonClick = (
    event: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ) => {
    props.onSubmit(contentInput);
    clearInput(inputElement, setContentInput);

    event.preventDefault();
  };

  return (
    <form>
      <input
        type="text"
        onChange={handleContentsInputChange}
        ref={(el) => setInputElement(el)}
      />
      <button onClick={handleSubmitButtonClick}>작성</button>
    </form>
  );
}

function clearInput(
  elm: HTMLInputElement | null,
  setContentInput: React.Dispatch<React.SetStateAction<string>>
) {
  if (elm) {
    elm.value = "";
  }
  setContentInput("");
}
