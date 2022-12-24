const BodyContainer = ({ children }) => {
  return (
    <section className="contact mt-5">
      <div className="container pt-3">
        <div className="row">
          <div className="col-md-12">{children}</div>
        </div>
      </div>
    </section>
  );
};

export default BodyContainer;
