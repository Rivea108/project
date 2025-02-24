document.addEventListener("DOMContentLoaded", () => {
    // Register GSAP Plugins
    gsap.registerPlugin(ScrollTrigger);
  
    // Parallax Layers Animation
    document.querySelectorAll('[data-parallax-layers]').forEach((triggerElement) => {
      let tl = gsap.timeline({
        scrollTrigger: {
          trigger: triggerElement,
          start: "top top", // Trigger at the top
          end: "bottom top", // End when the bottom reaches the top
          scrub: true // Smooth scrubbing effect
        }
      });
  
      // Define Layer Animations
      const layers = [
        { layer: "1", yPercent: 70 }, // Slowest layer
        { layer: "2", yPercent: 50 },
        { layer: "3", yPercent: 30 }, // Title layer
        { layer: "4", yPercent: 10 }  // Fastest layer
      ];
  
      // Apply animations to each layer
      layers.forEach((layerObj) => {
        tl.to(
          triggerElement.querySelectorAll(`[data-parallax-layer="${layerObj.layer}"]`),
          {
            yPercent: layerObj.yPercent,
            ease: "none" // Smooth easing
          },
          0 // All animations start together
        );
      });
    });
  
    // Smooth Scrolling using Lenis
    const lenis = new Lenis({
      duration: .8, // Adjust scrolling speed
      easing: (t) => t * (2 - t), // Default easing
    });
  
    lenis.on('scroll', ScrollTrigger.update);
    gsap.ticker.add((time) => {
      lenis.raf(time * 1000);
    });
  });

  document.addEventListener("DOMContentLoaded", () => {
    gsap.registerPlugin(ScrollTrigger);

    document.querySelectorAll('[data-parallax-layers]').forEach((triggerElement) => {
        let tl = gsap.timeline({
            scrollTrigger: {
                trigger: triggerElement,
                start: "top top",
                end: "bottom top",
                scrub: true
            }
        });


 
// .to(element, { x: value }) → 수평 이동
// .to(element, { yPercent: value }) → 스크롤 이동 효과
// .to(element, { scale: value }) → 크기 조정 가능
// .to(element, { rotate: value }) → 회전 가능

        // 개별 레이어 위치 조정 (애니메이션)
//         tl.to(triggerElement.querySelectorAll('[data-parallax-layer="1"]'), {
//             yPercent: 70,
//             x: -50,  // 왼쪽 이동
//             scale: 1.1,
//             ease: "none"
//         });

//         tl.to(triggerElement.querySelectorAll('[data-parallax-layer="2"]'), {
//             yPercent: 50,
//             x: 50,  // 오른쪽 이동
//             scale: 0.9,
//             ease: "none"
//         });

//         tl.to(triggerElement.querySelectorAll('[data-parallax-layer="3"]'), {
//             yPercent: 30,
//             x: 0,  // X축 이동 없음
//             rotate: -5,
//             ease: "none"
//         });
     });
});