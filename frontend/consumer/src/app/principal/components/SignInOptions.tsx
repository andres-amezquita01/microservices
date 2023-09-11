import React from "react";

import { SiFacebook } from "react-icons/si";
import { LuMousePointerClick } from "react-icons/lu";
import { FcGoogle } from "react-icons/fc";

interface Props {
  signInWithMethod: (label: string) => void;
}

const signInItems = [
  {
    name: "Sign up with Facebook",
    signInLabel: "facebook",
    icon: SiFacebook,
  },
  {
    name: "Sign up with Google",
    signInLabel: "google",
    icon: FcGoogle,
  },
  {
    name: "Sign up Active Directory",
    signInLabel: "azure",
    icon: LuMousePointerClick,
  },
];

export default function SignInOptions(props: Props) {
  return (
    <div className="flex-col w-full lg:w-3/4">
      {signInItems.map(({ name, signInLabel, icon: Icon }) => (
        <button
          key={name}
          onClick={() => props.signInWithMethod(signInLabel)}
          className="flex flex-row bg-white w-full justify-center p-3 text-xl items-center border border-black rounded-full mt-5"
        >
          <div className="px-4">
            <Icon />
          </div>
          <span>{name}</span>
        </button>
      ))}
    </div>
  );
}
