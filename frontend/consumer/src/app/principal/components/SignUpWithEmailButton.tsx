interface Props {
  onSignUp: () => void;
}
export default function SignUpWithEmailButton(props: Props) {
  return (
    <button
      className="bg-dark-blue text-white font-semibold"
      onClick={() => props.onSignUp}
    >
      Sign up with your email
    </button>
  );
}
