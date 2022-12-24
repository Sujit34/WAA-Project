import { Button } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router";
import { useEffect } from "react";
import $ from "jquery";

function JobsList(props) {

  const jobs = props.jobs;
  const action = props.action;
  const navigate = useNavigate();

  const openJobDetails = (id) => {
    navigate("/job-detail/" + id);
  };

  useEffect(() => {
    $("#datatable").DataTable();
  });
  
  return (
    <>
      {jobs.length > 0 && (
        <table className="table table-hover" id={props.enableFilter?"datatable":"stuTable"}>
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Title</th>
              <th scope="col">Company</th>
              <th scope="col">State</th>
              <th scope="col">City</th>
              <th scope="col">Application Deadline</th>
              <th scope="col">Tags</th>
              {action && <th scope="col">Actions</th>}
            </tr>
          </thead>
          <tbody>
            {jobs.map((item, index) => {
              return (
                <tr key={index}>
                  <th scope="row">
                    <Button onClick={() => openJobDetails(item.id)}>
                      {item.id}
                    </Button>
                  </th>
                  <td>{item.title}</td>
                  <td>{item.company}</td>
                  <td>{item.address?.state}</td>
                  <td>{item.address?.city}</td>
                  <td>{item.applicationEndDate}</td>
                  <td>
                    {item.tag &&
                      item.tag.map((t) => (
                        <span
                          key={t.id}
                          className="badge badge-pill badge-secondary"
                        >
                          {t.tag}
                        </span>
                      ))}
                  </td>
                  {action && (
                    <td>
                      <button
                        className="btn btn-sm btn-outline-primary mr-3"
                        onClick={() => action.onClicked(item.id)}
                      >
                        {action.name}
                      </button>
                    </td>
                  )}
                </tr>
              );
            })}
          </tbody>
        </table>
      )}
    </>
  );
}
export default JobsList;
