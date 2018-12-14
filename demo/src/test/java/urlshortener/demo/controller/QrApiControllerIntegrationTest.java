package urlshortener.demo.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QrApiControllerIntegrationTest {

    private static final String EXPECTED_QR_CODE = "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0AQAAAADjreInAAABg0lEQVR42u3cO3KDMBAAUGVcuOQIOQpHs4/mo/gIlCkyUcb8JAEJJplJEb0tgadyZ3clEeKvogs8z/M8z/M8z/M8z/P83/iPUMQpf9DEt/Jtw/M8X/iUVt4GH+M1tPHe+1N6e+d5nl/565xuTpsLPqLleZ7f90O88jzPH/NFucLzPL/jV+XKJd7m/HO8f+J5vhq/mr/m85OfzG95nq/Db8Xq84P7xzzP1+HHcuVRnQz5p/dT/RLje3jZWJDneb7wTf95yMclxfxkL//wPF+ZH9NNyj/jQOWRf7q8IQpjQuJ5nl/mn5jKlWncGsI5kdtO/cLzfHW+jyG7dPmotR39WNBcvtq/4Xm+cp9Hcf4se9A+Ob/leb4av9r/3Vpwr//heb5Wnx4v7s90U/+Tb+jwPM8vs0txf+bSj0vm/uf78yM8z/Pz/ZnU7iwWbHie55/zQ/2yOz/heb5mv+p/rkfOv/M8X6/fuH83nT87lwdKGp7nef+P5nme53me53me53me/+f+E5W4ETCek3pPAAAAAElFTkSuQmCC";
    @Autowired
    private QrApi api;



}
