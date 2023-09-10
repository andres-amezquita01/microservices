import styles from "./style.module.css";
import Link from "next/link";
import React from "react";

import { SiFacebook } from "react-icons/si";
import { LuMousePointerClick } from "react-icons/lu";
import { FcGoogle } from "react-icons/fc";

interface Props {
  signInWithMethod: (label: string) => void;
}

const signInItems = [
  {
    name: "Sing up with Facebook",
    signInLabel: "facebook",
    icon: SiFacebook,
  },
  {
    name: "Sing up with Google",
    signInLabel: "google",
    icon: FcGoogle,
  },
  {
    name: "Sing up Active Directory",
    signInLabel: "azure",
    icon: LuMousePointerClick,
  },
];

export default function ButtomSocial(props: Props) {
  return (
    <div className={styles.buttom__wrapper}>
      <div>
        {signInItems.map(({ name, signInLabel, icon: Icon }) => (
          <button
            className={styles.buttom__link_active}
            key={name}
            onClick={() => props.signInWithMethod(signInLabel)}
          >
            <div className={styles.buttom__icon}>
              <Icon />
            </div>
            <span className={styles.buttom__name}>{name}</span>
          </button>
        ))}
      </div>
    </div>
  );
}
