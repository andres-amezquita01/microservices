import React from "react";

import { SiFacebook } from "react-icons/si";
import { LuMousePointerClick } from "react-icons/lu";
import { FcGoogle } from "react-icons/fc";

interface Props {
  logInWithMethod: (label: string) => void;
}

const logInItems = [
  {
    name: "Login with Facebook",
    logInLabel: "facebook",
    icon: SiFacebook,
  },
  {
    name: "Login with Google",
    logInLabel: "google",
    icon: FcGoogle,
  },
  {
    name: "Login Active Directory",
    logInLabel: "azure",
    icon: LuMousePointerClick,
  },
];

export default function ButtomSocial(props: Props) {
  return (
    <div className="flex-col w-full ">
      {logInItems.map(({ name, logInLabel, icon: Icon }) => (
        <button
          key={name}
          onClick={() => props.logInWithMethod(logInLabel)}
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
