import React, { useEffect } from "react";
import { useNavigate } from "react-router";
import $ from "jquery";

function StudentsList(props) {
  const navigate = useNavigate();

  const onOpenClicked = (id) => {
    navigate("/student-detail/" + id);
  };
  
  useEffect(() => {
    $("#datatable").DataTable();
  });

  const students = props.students;
  return (
    <>
      {students.length > 0 && (
        <table className="table table-hover" id={props.enableFilter?"datatable":"stuTable"}>
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Student Id</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Email</th>
              <th scope="col">Major</th>
              <th scope="col">State</th>
              <th scope="col">City</th>
              <th scope="col">Actions</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student, index) => {
              return (
                <tr key={index}>
                  <th scope="row">{index + 1}</th>
                  <td>{student.studentId}</td>
                  <td>{student.firstName}</td>
                  <td>{student.lastName}</td>
                  <td>{student.email}</td>
                  <td>{student.major}</td>
                  <td>{student.address?.state}</td>
                  <td>{student.address?.city}</td>
                  <td>
                    <div className="card-body">
                      <button
                        onClick={() => onOpenClicked(student.id)}
                        className="btn btn-sm btn-outline-primary"
                      >
                        Open
                      </button>
                    </div>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </>
  );
}
export default StudentsList;
