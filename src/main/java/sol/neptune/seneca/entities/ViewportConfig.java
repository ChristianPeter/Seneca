/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.entities;

/**
 *
 * @author murdoc
 */
public enum ViewportConfig {
    FULL(1),
    HORIZONTAL_5_5(2), // 50% | 50%
    HORIZONTAL_3_7(2), // 30% | 70%
    VERTICAL_5_5(2), // 50% / 50%
    VERTICAL_7_3(2); // 70% / 30%
    
    private int numberOfPresentations;

    private ViewportConfig(int numberOfPresentations) {
        this.numberOfPresentations = numberOfPresentations;
    }
    
    
}
