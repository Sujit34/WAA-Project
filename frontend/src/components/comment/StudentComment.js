import React from "react";

function StudentComment(props) {
  const { comment } = props;
  return (
    <div className="card">
      <div className="card-body">
        <h6 className="card-subtitle mb-2 text-muted">
          {comment.faculty.firstName + " " + comment.faculty.lastName} (
          {comment.createdDate})
        </h6>
        <p className="card-text">{comment.comment}</p>
      </div>
    </div>
  );
}

export default StudentComment;
