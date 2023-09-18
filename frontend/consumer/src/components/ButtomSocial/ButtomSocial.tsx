import styles from "./style.module.css";
import Image from "next/image";
import Link from "next/link";
import React from 'react';

import { SiFacebook } from "react-icons/si"
import { LuMousePointerClick } from "react-icons/lu";
import { FcGoogle } from "react-icons/fc";
import { MdKeyboardArrowRight, MdKeyboardArrowLeft } from "react-icons/md";


const sidebarItems = [
  {
    name: "Login with Facebook",
    href: "/dashboard",
    icon: SiFacebook,
  },
  {
    name: "Login with Google",
    href: "/dashboard/stats",
    icon: FcGoogle,
  },
  {
    name: "Login with Active Directory",
    href: "/dashboard/inventory_plus",
    icon: LuMousePointerClick,
  },
];

export default function ButtomSocial() {
  return (
      <div className={"${styles.buttom__wrapper} sm:text-sm md:text-base lg:text-lg xl:text-xl"}>
        <div >
          <ul>
            {sidebarItems.map(({ name, href, icon: Icon }) => (
                <li className={styles.sidebar__item} key={name}>
                  <Link
                      className={
                         href
                            ? styles.buttom__link_active
                            : styles.buttom__link
                      }
                      href={href}
                  >
                    <div className={styles.buttom__icon}>
                      <Icon />
                    </div>
                    <span className={styles.buttom__name}>{name}</span>
                  </Link>
                  
                </li>
            ))}
          </ul>
          
        </div>
      </div>
  );
}