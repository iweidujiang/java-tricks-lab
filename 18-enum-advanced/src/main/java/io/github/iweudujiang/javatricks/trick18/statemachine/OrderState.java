package io.github.iweudujiang.javatricks.trick18.statemachine;

/**
 * 订单状态机
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/3/12
 */
public enum OrderState {
    PENDING {
        @Override
        public OrderState next() {
            return PAID;
        }
    },
    PAID {
        @Override
        public OrderState next() {
            return SHIPPED;
        }
    },
    SHIPPED {
        @Override
        public OrderState next() {
            return DELIVERED;
        }
    },
    DELIVERED {
        @Override
        public OrderState next() {
            return DELIVERED; // 最终状态
        }
    },
    CANCELLED {
        @Override
        public OrderState next() {
            return CANCELLED;
        }
    };

    public abstract OrderState next();

    public boolean canTransitionTo(OrderState target) {
        return this.next() == target;
    }
}
