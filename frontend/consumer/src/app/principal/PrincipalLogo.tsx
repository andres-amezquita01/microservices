import Image from "next/image";
import styles from "./principal.module.css";
import ButtomSocial from "@/components/ButtomSocialP/ButtomSocial"

export default function PrincipalLogin({width=200, height=200}: IProps) {

  return (
    <div className={styles.logo}>
      <ButtomSocial />
    </div>
  );
}

interface IProps {
  width?: number;
  height?: number;

}