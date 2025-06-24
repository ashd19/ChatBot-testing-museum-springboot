import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { useState, useEffect } from "react";
import { Sun, Moon } from "lucide-react";

function DarkLightToggle() {
  const [isDark, setIsDark] = useState(false);

  const toggleMode = () => setIsDark(!isDark);

  // Apply dark class to html element for Tailwind dark mode
  useEffect(() => {
    if (isDark) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [isDark]);

  return (
    <div className="min-h-screen transition-colors duration-300 bg-gray-50 dark:bg-gray-900">
      <div className="flex items-center justify-center min-h-screen">
        <div className="text-center space-y-8 w-full">
          {/* Content */}
          <div className="flex items-center justify-center gap-x-4 mb-8">
            <h1 className="scroll-m-20 text-4xl font-extrabold tracking-tight text-balance mb-0 text-gray-900 dark:text-white">
              Welcome
            </h1>
            <button
              onClick={toggleMode}
              className={`relative inline-flex items-center w-16 h-8 rounded-full transition-colors duration-300 focus:outline-none focus:ring-2 focus:ring-offset-2 mt-3 ${
                isDark 
                  ? 'bg-blue-600 focus:ring-blue-500' 
                  : 'bg-gray-300 focus:ring-gray-400'
              }`}
            >
              {/* Sliding Circle */}
              <span
                className={`inline-block w-6 h-6 transform transition-transform duration-300 bg-white rounded-full shadow-lg ${
                  isDark ? 'translate-x-9' : 'translate-x-1'
                }`}
              />
              {/* Sun Icon */}
              <Sun 
                className={`absolute left-1 w-4 h-4 transition-opacity duration-300 ${
                  isDark ? 'text-gray-400 opacity-50' : 'text-yellow-500 opacity-100'
                }`}
              />
              {/* Moon Icon */}
              <Moon 
                className={`absolute right-1 w-4 h-4 transition-opacity duration-300 ${
                  isDark ? 'text-blue-300 opacity-100' : 'text-gray-400 opacity-50'
                }`}
              />
            </button>
          </div>

          <Input 
            type="email"
            placeholder="email"
            className="mb-5 w-80 mx-auto block"
          />
          
          <div className="flex mb-10 justify-center">
            <Button>Submit</Button>
          </div>
          
          <Input 
            type="text"
            placeholder="Enter your message.."
            className="mb-5 w-80 mx-auto block"
          />
          
          <Textarea
            placeholder="Response"
            className="w-96 h-24 mx-auto block"
          />
        </div>
      </div>
    </div>
  );
}

function App() {
  return <DarkLightToggle />;
}

export default App;