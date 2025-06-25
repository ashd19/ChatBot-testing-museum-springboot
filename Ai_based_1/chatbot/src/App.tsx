import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { useState, useEffect } from "react";
import { Sun, Moon } from "lucide-react";
import axios from "axios";

function DarkLightToggle() {
  const [isDark, setIsDark] = useState(false);
  const [email, setEmail] = useState("");
  const [emailError, setEmailError] = useState("");
  const [success, setSuccess] = useState(false);

  const [question, setQuestion] = useState("");
  const [answer, setAnswer] = useState("");
  const [questionError, setQuestionError] = useState("");

  const toggleMode = () => setIsDark(!isDark);

  useEffect(() => {
    if (isDark) {
      document.documentElement.classList.add('dark');
    } else {
      document.documentElement.classList.remove('dark');
    }
  }, [isDark]);

  // Email validation function
  const validateEmail = (value: string) => {
    if (!value) return "Email cannot be empty";
    // Simple regex for email validation
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!re.test(value)) return "Please enter a valid email address";
    return "";
  };

  const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(e.target.value);
    setEmailError(validateEmail(e.target.value));
    setSuccess(false);
  };

  const handleEmailSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const error = validateEmail(email);
    setEmailError(error);
    if (!error) {
      try {
        // Adjust the URL and payload as per your backend
        await axios.post("http://localhost:8080/api/qna/post", { email });
        setSuccess(true);
      } catch (err) {
        setEmailError("Failed to save email. Try again.");
        setSuccess(false);
      }
    } else {
      setSuccess(false);
    }
  };

  // Question/AI logic
  const handleQuestionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setQuestion(e.target.value);
    setQuestionError("");
    setAnswer("");
  };

  const handleAsk = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!question.trim()) {
      setQuestionError("Question cannot be empty");
      setAnswer("");
      return;
    }
    setQuestionError("");
    setAnswer("Loading...");
    try {
      const res = await axios.post("http://localhost:8080/api/qna/ask", { question });
      setAnswer(res.data);
    } catch (err) {
      setAnswer("Failed to get answer from AI.");
    }
  };

  return (
    <div className="min-h-screen transition-colors duration-300 bg-gray-50 dark:bg-gray-900">
      <div className="flex items-center justify-center min-h-screen">
        <div className="text-center space-y-8 w-full">
          {/* Content */}
          <div className="flex items-center justify-center gap-x-4 mb-8">
            <h1 className="scroll-m-20 text-4xl font-extrabold tracking-tight text-balance mb-0 text-gray-900 dark:text-white">
              Welcome To Nehru Planetarium Chatbot
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

          {/* Email Form */}
          <form onSubmit={handleEmailSubmit}>
            <Input 
              type="email"
              placeholder="email"
              className="mb-2 w-80 mx-auto block"
              value={email}
              onChange={handleEmailChange}
            />
            {emailError && (
              <div className="text-red-500 mb-2">{emailError}</div>
            )}
            <div className="flex mb-10 justify-center">
              <Button type="submit" disabled={!!emailError || !email}>
                Submit
              </Button>
            </div>
          </form>

          {success && (
            <h1 className="text-green-600 text-xl font-bold mb-4">
              Email submitted successfully!
            </h1>
          )}

          {/* Question/AI Form */}
          <form onSubmit={handleAsk}>
            <Input 
              type="text"
              placeholder="Enter your message.."
              className="mb-2 w-80 mx-auto block"
              value={question}
              onChange={handleQuestionChange}
            />
            {questionError && (
              <div className="text-red-500 mb-2">{questionError}</div>
            )}
            <div className="flex mb-5 justify-center">
              <Button type="submit" disabled={!question.trim()}>
                Ask
              </Button>
            </div>
          </form>
          
          <Textarea
            placeholder="Response"
            className="w-96 h-24 mx-auto block"
            value={answer}
            readOnly
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