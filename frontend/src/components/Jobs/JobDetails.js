import axios from "axios";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import { getUser } from "../../service/tokenService";
import BodyContainer from "../body-container/BodyContainer";
import StudentsList from "../student/StudentList";

function JobDetails(props) {
  const { id } = useParams();
  const [job, setJob] = useState({});
  const [applicants, setApplicants] = useState({});

  useEffect(() => {
    getDetails();
    getApplications();
  }, []);

  const getDetails = () => {
    axios.get("jobAdvertisements/" + id).then((response) => {
      setJob(response.data);
    });
  };

  const getApplications = () => {
    axios.get("jobApplications/filterByJobId?id=" + id).then((response) => {
      console.log(response);
      const applicantList = response.data?.map(
        (application) => application.applicant
      );
      setApplicants(applicantList);
    });
  };

  const download = (url) => {
    axios({
      url: url, //your url
      method: "GET",
      responseType: "blob", // important
    }).then((response) => {
      // create file link in browser's memory
      const href = URL.createObjectURL(response.data);

      // create "a" HTML element with href to file & click
      const link = document.createElement("a");
      link.href = href;
      link.setAttribute("download", "description.txt"); //or any other extension
      document.body.appendChild(link);
      link.click();

      // clean up "a" element & remove ObjectURL
      document.body.removeChild(link);
      URL.revokeObjectURL(href);
    });
  };

  return (
    <BodyContainer>
      <h1 className="mb-4">Job Details</h1>
      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="title" className="form-label">
            Title:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.title}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="jobDescription" className="form-label">
            Description:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.jobDescription}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="company" className="form-label">
            Company:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.company}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="contact" className="form-label">
            Contact:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.contact}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="address" className="form-label">
            Address:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.address?.street +
              ", " +
              job.address?.city +
              ", " +
              job.address?.state +
              ", " +
              job.address?.zipCode}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="tag" className="form-label">
            Tags:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.tag?.map((t) => (
              <span key={t.id} className="badge badge-pill badge-secondary">
                {t.tag}
              </span>
            ))}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="applicationEndDate" className="form-label">
            Application End Date:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.applicationEndDate}
          </div>
        </div>
      </div>

      <div className="col-md-12 mb-3">
        <div className="form-group">
          <label htmlFor="entryYear" className="form-label">
            Attached File:
          </label>
          <div className="form-control form-control-lg bg-white">
            {job.filePath && (
              <button onClick={() => download(job.filePath)}>
                Download Attachment
              </button>
            )}
          </div>
        </div>
      </div>

      {job.owner?.email === getUser().email && (
        <div className="col-md-12 mb-3">
          <div className="form-group">
            <label htmlFor="entryYear" className="form-label">
              Applicants:
            </label>
            <div className="form-control form-control-lg bg-white">
              <StudentsList students={applicants} />
            </div>
          </div>
        </div>
      )}
    </BodyContainer>
  );
}

export default JobDetails;
