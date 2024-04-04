/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],  theme: {
    extend: {
      zIndex: {
        '1000': 1000,
      }},
  },
  plugins: [require('tailwind-scrollbar-hide')],
}
