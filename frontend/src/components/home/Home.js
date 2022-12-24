import React, { useEffect } from "react";
import { useNavigate } from "react-router";
import { hasToken } from "../../service/tokenService";
import BodyContainer from "../body-container/BodyContainer";
import { EchartDashboard } from "../dashboard/EchartDashboard";
import Last10AppliedJobs from "../dashboard-lists/Last10AppliedJobs";
import Last10Jobs from "../dashboard-lists/Last10Jobs";

function Home() {
  const navigate = useNavigate();

  useEffect(() => {
    if (!hasToken()) navigate("/login");
  }, []);

  return (
    <BodyContainer>
      <Last10Jobs />
      <hr />
      <Last10AppliedJobs />
      <hr />
      <div className="mt-5">
        <h1 className="pb-3">Charts</h1>
        <EchartDashboard>
        </EchartDashboard>
      </div>
    </BodyContainer>
  );
}

export default Home;
