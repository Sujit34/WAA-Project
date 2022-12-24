import axios from "axios";
import React, { useRef, useState } from "react";
import PopupModal from "../popup-modal/PopupModal";

export default function AdminChangePassword(props) {
  const formRef = useRef();
  const [showModal, setShowModal] = useState(false);
  const onChangePasswordClicked = () => setShowModal(true);
  const onClose = () => setShowModal(false);

  const onSubmitClicked = (event) => {
    event.preventDefault();
    // console.log(formRef.current.password.value);

    if (formRef.current && formRef.current.password)
      axios
        .put(`/admin/users/change-password`, {
          userId: props.id,
          newPassword: formRef.current.password.value,
        })
        .then(() => {
          window.alert("Pasword Changed");
          onClose();
        })
        .catch((error) => {
          console.log(error);
        });
  };

  return (
    <>
      <button
        onClick={onChangePasswordClicked}
        className="btn btn-sm btn-outline-primary"
      >
        Change Password
      </button>
      <PopupModal
        handleClose={onClose}
        handleOpen={onChangePasswordClicked}
        open={showModal}
      >
        <form
          className="form-a contactForm"
          onSubmit={onSubmitClicked}
          ref={formRef}
        >
          <div className="row">
            <div className="col-md-12 mb-3">
              <div className="form-group">
                <label htmlFor="password" className="form-label">
                  New Password:
                </label>
                <input
                  id="password"
                  name="password"
                  type="password"
                  className="form-control form-control-lg form-control-a"
                  required
                />
              </div>
            </div>
          </div>
          <button type="submit" className="btn btn-primary">
            Change Password
          </button>
        </form>
      </PopupModal>
    </>
  );
}
