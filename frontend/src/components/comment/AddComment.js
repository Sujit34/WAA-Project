import axios from "axios";
import React, { useContext, useRef, useState } from "react";
import { getUser } from "../../service/tokenService";
import PopupModal from "../popup-modal/PopupModal";
import { StudentContext } from "../student/StudentDetails";

export default function AddComment(props) {
  const student = useContext(StudentContext);
  const formRef = useRef();
  const [showModal, setShowModal] = useState(false);
  const onAddCommentClicked = () => setShowModal(true);
  const onClose = () => setShowModal(false);

  const onSubmitClicked = (event) => {
    event.preventDefault();

    if (formRef.current && formRef.current.comment)
      axios
        .post("/comments", {
          comment: formRef.current.comment.value,
          facultyEmail: getUser().email,
          studentEmail: student.email,
        })
        .then(() => {
          props.onAddSuccess();
          onClose();
        })
        .catch((error) => {
          console.log(error);
        });
  };

  return (
    <>
      <div>
        <button
          onClick={onAddCommentClicked}
          className="btn btn-sm btn-primary"
        >
          Add Comment
        </button>
      </div>
      <PopupModal
        handleClose={onClose}
        handleOpen={onAddCommentClicked}
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
                <label htmlFor="comment" className="form-label">
                  Comment:
                </label>
                <textarea
                  id="comment"
                  name="comment"
                  className="form-control form-control-lg form-control-a"
                  required
                />
              </div>
            </div>
          </div>
          <button type="submit" className="btn btn-primary">
            Post Comment
          </button>
        </form>
      </PopupModal>
    </>
  );
}
