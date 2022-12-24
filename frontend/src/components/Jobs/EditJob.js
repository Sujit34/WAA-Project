import React, { useEffect, useRef, useState } from "react";
import BodyContainer from "../body-container/BodyContainer";
import { useNavigate, useParams } from "react-router-dom";
import { getUser } from "../../service/tokenService";
import axios from "axios";

function EditJob() {
  const formRef = useRef();
  const navigate = useNavigate();
  const [selectedFile, setSelectedFile] = useState(null);
  const { id } = useParams();

  useEffect(() => {
    getJobDetails();
  }, []);

  const getJobDetails = () => {
    axios.get("/jobAdvertisements/" + id).then((response) => {
      console.log(response);
      const form = formRef.current;

      form.jobtitle.value = response.data.title;
      form.desc.value = response.data.jobDescription;
      form.companyname.value = response.data.company;
      form.contact.value = response.data.contact;
      form.street.value = response.data.address?.street;
      form.city.value = response.data.address?.city;
      form.state.value = response.data.address?.state;
      form.zipcode.value = response.data.address?.zipCode;
      form.tag.value = response.data.tag.map(item => item.tag).join(", ");
      form.applicationEndDate.value = response.data.applicationEndDate;

    });
  };

  const handleFileSelect = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  function updateJob(data) {
    console.log(data);
    axios
      .put("/jobAdvertisements/" + id, data)
      .then(() => {
        navigate("/my-jobs");
      })
      .catch((error) => {
        console.log(error.response.data);
        const errorData = error.response.data.error;
        const validationErrors = errorData.validationErrors;
        console.log(validationErrors);
        //todo: handle error properly .. show snackbar with proper error message, we might get from server.
        if (validationErrors) {
          alert(validationErrors);
        }
      });
  }

  function onSubmitClicked(e) {
    e.preventDefault();
    const form = formRef.current;

    const data = {
      owner: { email: getUser().email },
      title: form.jobtitle.value,
      jobDescription: form.desc.value,
      company: form.companyname.value,
      contact: form.contact.value,
      address: {
        street: form.street.value,
        city: form.city.value,
        state: form.state.value,
        zipCode: form.zipcode.value,
      },
      tag: form.tag?.value.split(",").map((tag) => {
        return { tag: tag.trim() };
      }),
      applicationEndDate: form.applicationEndDate.value,
      files: selectedFile,
    };
    updateJob(data);
  }

  return (
    <BodyContainer>
      <h1 className="mb-4">Edit Job</h1>
      <form
        className="form-a contactForm"
        ref={formRef}
        onSubmit={onSubmitClicked}
      >
        <div className="row">
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"email"} className="form-label">
                Title:{" "}
              </label>
              <input
                id={"jobtitle"}
                name={"jobtitle"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
                minLength={8}
                required
              />
            </div>
          </div>
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"password"} className="form-label">
                Description:{" "}
              </label>
              <textarea
                name={"desc"}
                id={"desc"}
                className="form-control form-control-lg form-control-a"
                required
              ></textarea>
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"fname"} className="form-label">
                Company Name:{" "}
              </label>
              <input
                name={"companyname"}
                id={"companyname"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
                required
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"street"} className="form-label">
                Street:{" "}
              </label>
              <input
                name={"street"}
                id={"street"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"city"} className="form-label">
                City:{" "}
              </label>
              <input
                name={"city"}
                id={"city"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"state"} className="form-label">
                State:{" "}
              </label>
              <input
                name={"state"}
                id={"state"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"zipcode"} className="form-label">
                Zip Code:{" "}
              </label>
              <input
                name={"zipcode"}
                type={"text"}
                id={"zipcode"}
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"contact"} className="form-label">
                Contact:{" "}
              </label>
              <input
                name={"contact"}
                id={"contact"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"applicationEndDate"} className="form-label">
                Application Deadline:{" "}
              </label>
              <input
                name={"applicationEndDate"}
                id={"applicationEndDate"}
                type={"date"}
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"tag"} className="form-label">
                Tags:{" "}
              </label>
              <input
                name={"tag"}
                id={"tag"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
              />
            </div>
          </div>

          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"phoneNumber"} className="form-label">
                Attachments:{" "}
              </label>
              <input
                name={"file"}
                id={"file"}
                type={"file"}
                className="form-control form-control-lg form-control-a"
                onChange={handleFileSelect}
              />
            </div>
          </div>

          <div className="col-md-12 text-right mb-4">
            <button type="submit" className="btn btn-primary">
              Save
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
}

export default EditJob;
