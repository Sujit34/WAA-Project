import axios from "axios";
import React, { useEffect, useState } from "react";
import BodyContainer from "../body-container/BodyContainer";
import StudentsList from "./StudentList";

function Students() {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    axios
      .get("/students")
      .then((response) => {
        setStudents(response.data);
      })
      .catch((error) => {
        console.log(error.message);
      });
  }, []);

  return (
    <BodyContainer>
      <section className="intro-single p-2">
        <div className="container">
          <div className="row">
            <div className="title-single-box">
              <h1 className="title-single">Students</h1>
            </div>
          </div>
        </div>
      </section>

      <StudentsList students={students} enableFilter={true} />
    </BodyContainer>
  );
}

export default Students;
