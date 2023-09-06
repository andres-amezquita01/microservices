import Image from "next/image";
import styles from "./login.module.css";
import ButtomSocial from "@/components/ButtomSocial/ButtomSocial"
export default function LogoLogin({width=200, height=200}: IProps) {

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