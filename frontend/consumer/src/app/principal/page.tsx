"use client";
import ButtomSocial from "@/components/ButtomSocialP/ButtomSocial";
import { signIn } from "next-auth/react";
import SignUpWithEmailButton from "./components/SignUpWithEmailButton";

function PricipalPage() {
  const signInWithMethod = async (method: string) => {
    signIn(method, { callbackUrl: "https://localhost:3000" });
  };

  const submitWithEmailPassword = async () => {
    console.log("TODO");
  };

  return (
    <>
      <ButtomSocial signInWithMethod={signInWithMethod} />
      <SignUpWithEmailButton onSignUp={submitWithEmailPassword} />
    </>
  );
}

export default PricipalPage;
