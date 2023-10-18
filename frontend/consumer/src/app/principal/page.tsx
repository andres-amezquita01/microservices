"use client";

import { signIn} from "next-auth/react";
import SignUpWithEmailButton from "./components/SignUpWithEmailButton";
import SignInOptions from "./components/SignInOptions";
import PrincipalLogo from "./components/PrincipalLogo";
import { SloganTitle } from "./components/SloganTitle";
import { OrSeparator } from "./components/OrSeparator";
import { PrivacyAndTerms } from "./components/PrivacyAndTerms";
import LoginRedirector from "./components/LoginRedirector";
import { useEffect } from "react";
import { useSession} from "next-auth/react";
import { redirect } from 'next/navigation'
 

function PrincipalPage() {
  const { status } = useSession();

  useEffect(() => {
    if(status == "authenticated"){
      redirect("/home")
    }
  }, [status]);

  const signInWithMethod = async (method: string) => {
    signIn(method, { callbackUrl: "/home" });
  };

  return (
    <div className="flex flex-row w-screen h-fill h-screen">
      <div className="flex flex-col w-full h-fill align-center justify-center m-4 lg:m-20">
        <SloganTitle />
        <SignInOptions signInWithMethod={signInWithMethod} />
        <OrSeparator />
        <SignUpWithEmailButton/>
        <PrivacyAndTerms />
        <LoginRedirector />
      </div>
      <div className="hidden w-full h-fill lg:flex">
        <PrincipalLogo />
      </div>
    </div>
  );
}

export default PrincipalPage;
