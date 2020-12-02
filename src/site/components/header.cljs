(ns site.components.header)

(defn header
  []
  [:<>
   [:div {:class "grid justify-items-center"}
       [:svg {:xmlns "http://www.w3.org/2000/svg" :class "mr-3 h-16 w-16" :viewBox "0 0 465 465" :height "620" :width "620" :version "1.0"}
        [:ellipse {:fill-rule "evenodd" :fill "#88001b" :cx "228.837" :cy "232.948" :rx "111.634" :ry "142.193"}]
        [:path {:stroke-width "7.75" :stroke "#3f48cc" :fill "#fff" :d "M52.125 167.063s18.188 24.562 26.25 29.625c8.063 5.062 33.375 29.812 74.813 37.875 41.437 8.062 78.375 4.5 78.375 4.5s13.125-.188 21.187-1.313c8.063-1.125 18.938-2.625 25.125-5.438 6.188-2.812 16.875-5.437 21.563-9.937 4.687-4.5 12.562-14.438 15-20.625 2.437-6.188 9.562-17.625 4.125-17.625-5.438 0-18.75.375-25.688-1.688-6.938-2.062-15.75-7.125-15.938-15.937-.187-8.813 6.188-16.5 12-18.375 5.813-1.875 9.375-4.313 24.375-4.125 15 .188 20.625 12 21.938 13.688 1.313 1.687 4.688 8.812 6.938 11.437s9.437 4.523 9 7.875c-.563 4.313.562 4.219 3.093 7.219 2.493 2.954 5.531 4.031 11.156 2.719 4.104-.958 9.375-3.188 10.313-7.313.95-4.177-9-10.5-15.188-9.75-5.241.635 4.125-3 30-3.563 8.444-.183 20.625 1.313 23.813 2.438 3.188 1.125 10.125 3.563 11.063 5.625.937 2.063 2.25-10.125 6.375-10.875s6.75-1.875 10.125.375 3 7.313 2.25 10.688c-.75 3.375-5.813 8.25-8.813 7.312-3-.938-9.188-7.875-9.563-6s-1.5 9-4.5 9.938c-3 .937-14.064 3.728-18.562 4.125-4.49.395-13.625.67-15.563.75C385.42 190.76 375 192 378 195.563c3 3.562 20.438 14.062 24.375 15.375 3.938 1.312 6.75 1.312 6.188 2.25-.563.937-5.625 6.562-12.188 7.312-6.563.75-17.813 1.125-25.875.188-8.063-.938-20.438-4.313-20.438-4.313s-2.437-3.563-2.437-.75-.188 12.375-1.5 16.875c-1.313 4.5-6.563 13.688-8.25 17.063-1.688 3.375-9.938 13.312-5.438 11.812 4.5-1.5 13.87-6.794 33-7.125 18.77-.325 23.898 3.61 29.643 5.405 5.287 1.653 6.745 3.243 12.797 8.79.57.522-4.245 1.471-8.913.902-5.616-.686-14.664-.748-23.267.077-8.625.828-21.603-1.955-18.228-1.018 3.375.938 26.906 7.594 32.156 9.844 5.25 2.25 25.875 10.875 28.875 12.563 3 1.687 23.25 11.625 25.312 15.562 2.063 3.938 7.313 6.938 1.688 6.188S416.625 303 411 301.5s-33.375-4.125-40.313-4.688c-6.937-.562-19.5-.75-27.562-.937-8.063-.188-21.938-1.875-23.813-.75s-13.5 9.563-24.562 12c-11.063 2.438-22.313 6-41.625 6.75-19.313.75-32.813-.188-40.688-2.25-7.875-2.063-15.937-6.938-17.25-5.625-1.312 1.313-5.437 11.438-9.562 14.063-4.125 2.625-19.125 12.375-32.25 18s-24.375 10.875-42.563 12.75c-18.187 1.875-48.937.375-54.562-.188s-17.813-2.25-14.063-3.188c3.75-.937 2.813 1.125 24.376-4.312 21.562-5.438 33.75-8.813 46.687-14.438 12.938-5.625 29.625-15.375 27.563-15.562-2.063-.188-12.75 8.438-23.813 9.938-11.063 1.5-36.75 3.562-46.125 2.625-9.375-.938-25.125-2.813-34.125-7.125-9-4.313-25.875-12.938-22.688-13.125 3.188-.188 26.813.937 40.126 0 13.312-.938 39.374-3.563 45.374-6 6-2.438 16.126-9.75 18-15.375 1.876-5.625 4.5-14.625 4.876-18.938.374-4.313 2.624-6 .187-7.313-2.438-1.312-24-7.875-29.625-12.562-5.625-4.688-18.938-14.813-23.625-20.813-4.688-6-15.938-20.625-18.563-26.625-2.624-6-5.25-13.125-5.062-18.75.188-5.625 1.5-14.062 2.438-14.437.937-.375 3.937 2.438 3.937 2.438z"}]
        [:path {:stroke-width "7.75" :stroke "#3f48cc" :fill "none" :d "M365.063 169.125s-4.875 0-8.25 1.5-5.063 5.25-5.625 6.188c-.563.937.375 0 .375 0"}]
        [:path {:stroke-width "7.182" :stroke "#3f48cc" :fill "none" :d "M331.043 261.355s7.841.866 13.243 2.426 16.965 5.907 14.464 5.025c-19.39-6.835-5.315-1.906-5.315-1.906"}]
        [:path {:fill-rule "evenodd" :fill "#88001b" :d "M57.806 52.193a12.463 9.546 0 010 .045H45.343z"}]]]
   [:div {:class "text-center text-3xl"} "Seven GUI Tasks"]])
