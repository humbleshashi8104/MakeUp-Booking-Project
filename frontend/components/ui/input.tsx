type InputProps = {
  label?: string;
  type?: string;
  name?: string;
  value?: string;
  onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
  placeholder?: string;
};
  
  export default function Input({
    label,
    type = "text",
    value,
    onChange,
    placeholder,
  }: InputProps) {
    return (
      <div className="flex flex-col gap-1">
        {label && <label className="text-sm font-medium">{label}</label>}
  
        <input
          name={name}
          type={type}
          value={value}
          onChange={onChange}
          placeholder={placeholder}
          className="border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-pink-500"
        />
      </div>
    );
  }