import clsx from "clsx";

type ButtonProps = {
  children: React.ReactNode;
  onClick?: () => void;
  variant?: "primary" | "secondary";
  className?: string;
};

export default function Button({
  children,
  onClick,
  variant = "primary",
  className,
}: ButtonProps) {
  return (
    <button
      type="button"
      onClick={onClick}
      className={clsx(
        "px-4 py-2 rounded-lg font-medium transition",
        variant === "primary" &&
          "bg-pink-600 text-white hover:bg-pink-700",
        variant === "secondary" &&
          "bg-gray-200 text-gray-800 hover:bg-gray-300",
        className
      )}
    >
      {children}
    </button>
  );
}
