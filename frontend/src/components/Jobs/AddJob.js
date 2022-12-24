import React, { useRef } from "react";
import BodyContainer from "../body-container/BodyContainer";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { getUser } from "../../service/tokenService";
import $ from "jquery";
function AddJob() {
  const formRef = useRef();
  const navigate = useNavigate();
  const [selectedFile, setSelectedFile] = React.useState(null);

  const handleFileSelect = (event) => {
    setSelectedFile(event.target.files[0]);
  };

  function postJobAd(data) {
    console.log(data);
    axios
      .post("/jobAdvertisements", data)
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
    const link = googleCloudUpload(selectedFile);
    const form = formRef.current;

    const data = {
      owner: { email: getUser().email },
      title: form.title.value,
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
      filePath: link
    };
    postJobAd(data);
  }
  function googleCloudUpload(file){
    return;
    const filename = Math.random() * Date.now() + file.name;
       var form = new FormData();
    form.append("", file);

    var settings = {
      "async": true,
      "crossDomain": true,
      "url": "https://storage.googleapis.com/upload/storage/v1/b/waa-final-project/o?uploadType=media&name="+filename+"",
      "method": "POST",
      "headers": {
        "Authorization": "Bearer ya29.a0AX9GBdUrfOZdXFzk5CgPcrDuzHlm9Vk39nBady8HG19q3oRezWVjzziKxl2iNARJKfdFFyo_H4DUFgx2fFjXbRxFSuHAzlOD29g9MsBE68B_G0zeGWT54vZXWh27T0Y8o1rIEUrfq2r4wYkb9eE8q_dNrqAE4PZjC1_061ZPP6rQz6gliRlzU-i8fIcw8xDmMi4veOsdocfJm-LatQpPoU2yihX1aWSsTQ_UqAaCgYKATMSARISFQHUCsbCbfGbdvdbfUGmoFrVM20cSQ0237"
      },
      "processData": false,
      "contentType": false,
      "mimeType": "multipart/form-data",
      "data": form
    }
    var linkG = '';
    $.ajax(settings).done(function (response) {
      console.log(response);
      var parsResponse = JSON.parse(response);
       linkG = parsResponse.mediaLink;
    });
    return linkG;
  }
  return (
    <BodyContainer>
      <h1 className="mb-4">Add New Job</h1>
      <form className="form-a contactForm" ref={formRef} onSubmit={onSubmitClicked}>
        <div className="row">
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"title"} className="form-label">
                Title:{" "}
              </label>
              <input
                id={"title"}
                name={"title"}
                type={"text"}
                className="form-control form-control-lg form-control-a"
                minLength={8}
                required
              />
            </div>
          </div>
          <div className="col-md-12 mb-3">
            <div className="form-group">
              <label htmlFor={"desc"} className="form-label">
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
            <button
              type="submit"
              className="btn btn-primary"
            >
              Post Job
            </button>
          </div>
        </div>
      </form>
    </BodyContainer>
  );
}

export default AddJob;
