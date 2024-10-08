// tailwind.config.js
import aspectRatio from "@tailwindcss/aspect-ratio";

export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {},
  },
  plugins: [
    aspectRatio, // Using import for the aspect-ratio plugin
  ],
};
