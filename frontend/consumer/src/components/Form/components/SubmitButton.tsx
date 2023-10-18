
import styles from "./styles.module.scss";
const Loader: React.FC = () => {
  return <div>Loading...</div>;
}

interface SubmitButtonProps {
buttonText: string;
isLoading?: boolean;
}


export function SubmitButton({ buttonText, isLoading }: SubmitButtonProps) {
return (
  <button className={styles.submitButton} type="submit" disabled={isLoading}>
    {isLoading ? "Cargando..." : buttonText}
  </button>
);
}


