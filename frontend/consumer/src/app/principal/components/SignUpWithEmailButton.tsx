interface Props {
  onSignUp: () => void;
}
export default function SignUpWithEmailButton(props: Props) {
  return (
    <button
      className="bg-dark-blue text-white rounded-full p-4 mt-5 w-full text-xl lg:w-3/4"
      onClick={() => props.onSignUp}
    >
      Sign up with your email
    </button>
  );
}
