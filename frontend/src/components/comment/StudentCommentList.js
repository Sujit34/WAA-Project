import axios from "axios";
import React, { useContext, useEffect, useState } from "react";
import { StudentContext } from "../student/StudentDetails";
import AddComment from "./AddComment";
import StudentComment from "./StudentComment";

function StudentCommentList(props) {
  const student = useContext(StudentContext);
  const [comments, setComments] = useState([]);

  const getComments = () => {
    axios
      .get("/comments/filterByStudentId?id=" + props.studentId)
      .then((response) => {
        setComments(response.data);
      });
  };

  useEffect(() => {
    getComments();
  }, []);

  return (
    <div className="card">
      <div className="card-header">Comments</div>
      {comments && comments.length > 0 ? (
        <ul className="list-group list-group-flush">
          {comments.map((comment, index) => (
            <li key={index} className="list-group-item">
              <StudentComment key={index} comment={comment} />
            </li>
          ))}
        </ul>
      ) : null}
      {props.showAdd && <AddComment onAddSuccess={() => getComments()} />}
    </div>
  );
}

export default StudentCommentList;
